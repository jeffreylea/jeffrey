package com.learn.jeffrey.snowflake;

import com.learn.jeffrey.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>雪花算法实现主键ID生成</p>
 * @author lijianfei
 * @date 2019-12-11
 */
@Slf4j
public class SnowFlakeIdGenerator {
	
	private static SnowFlakeIdGenerator generator = new SnowFlakeIdGenerator();
	/**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;
 
    /**
     * 每一部分占用的位数
     */
    //序列号占用的位数
    private final static long SEQUENCE_BIT = 12;
    //机器标识占用的位数
    private final static long MACHINE_BIT = 5;
    //数据中心占用的位数
    private final static long DATACENTER_BIT = 5;
 
    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
 
    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
 
    private long datacenterId;
    private long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;
    
    /**
     * 单例模式实现
     * @return
     */
    public static SnowFlakeIdGenerator getGenerator() {
    	if(generator==null) {
    		generator=new SnowFlakeIdGenerator();
    	}
    	return generator;
    }
    
    private SnowFlakeIdGenerator() {
    	this.datacenterId = 1L;
        this.machineId = 1L;
        this.lastStmp = System.currentTimeMillis();
    }
 
    /**
     * 构造器
     *
     * @param datacenterId 数据中心id (32之内,0~31)
     * @param machineId    机器标识id (32之内,0~31)
     */
    public SnowFlakeIdGenerator(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        /*if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            datacenterId = datacenterId & (MAX_DATACENTER_NUM - 1);
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            machineId = machineId & (MAX_MACHINE_NUM - 1);
        }*/
        this.datacenterId = datacenterId;
        this.machineId = machineId;
        this.lastStmp = System.currentTimeMillis();
    }
 
    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
 
        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
 
        lastStmp = currStmp;
 
        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }
 
    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }
 
    private long getNewstmp() {
        return System.currentTimeMillis();
    }
    
    /**
     * Get the worker id by using the last x bits of the local ip address
     * @throws Exception 
     * @throws UnknownHostException 
     */
    public static long getWorkerIdByIP(int bits) throws GenerateIdException {
    	int shift = 64 - bits;
    	try {
			InetAddress address = InetAddress.getLocalHost();
			long ip = IpUtils.ipV4ToLong(address.getHostAddress());
			long workerId = (ip << shift) >>> shift;
			return workerId;
		} catch (UnknownHostException e) {
			log.error("Generate unique id exception. ", e);
            throw new GenerateIdException(e);
		}
    }
    
    /**
     * 生成ID静态方法:雪花算法+IP后六位
     * @return
     * @throws Exception 
     */
    public static String generateId() throws GenerateIdException{
         try {
        	 InetAddress address = InetAddress.getLocalHost();
        	 String flag = IpUtils.getIpFlag(address.getHostAddress());
        	 return String.valueOf(generator.nextId())+flag;
		} catch (UnknownHostException e) {
			log.error("Generate unique id exception. ", e);
            throw new GenerateIdException(e);
		}
	}
    public static void main(String[] args) throws IOException {
    	
    	/*Map<String, String> map = new HashMap<String, String>();
    	Integer integer=1;
    	Convert.convert(HashMap.class, integer);*/
    	 File t = new File("D:\\\\2.txt");
  	   Writer  out = new FileWriter(t);
        /**
         *  datacenterId 数据中心id
         *  machineId    机器标识id
         */
        long datacenterId = 1L;
        long machineId = 1L;
        System.out.println("------------------"+machineId);
        System.out.println(generator.getClass());
        List<SnowFlakeIdGenerator> li=new ArrayList<>();
        for (int i = 0; i < (1 << 2); i++) {
        	SnowFlakeIdGenerator a=new SnowFlakeIdGenerator();
        
        	SnowFlakeIdGenerator a1=new SnowFlakeIdGenerator();
        	SnowFlakeIdGenerator a2=new SnowFlakeIdGenerator();
        	li.add(a);
        	li.add(a1);
        	li.add(a2);
        	System.out.println(li.size());
        	String b=a.generateId();
           out.write(a.generateId()+"\n");
        }
        out.close();
        long machineId1 = Long.parseLong(IpUtils.getIpFlag("127.0.0.2"));
        SnowFlakeIdGenerator snowFlake1 = new SnowFlakeIdGenerator(datacenterId, machineId1);
        System.out.println("------------------"+machineId1);
        
        for (int i = 0; i < (1 << 4); i++) {
            System.out.println(snowFlake1.nextId());
        }
    }


}
