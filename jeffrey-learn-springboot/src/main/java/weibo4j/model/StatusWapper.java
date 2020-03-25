package weibo4j.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class StatusWapper {

	private List<Status> statuses;

	private long previousCursor;

	private long nextCursor;

	private long totalNumber;
	
	private String hasvisible;

	public StatusWapper(List<Status> statuses, long previousCursor,
			long nextCursor, long totalNumber,String hasvisible) {
		this.statuses = statuses;
		this.previousCursor = previousCursor;
		this.nextCursor = nextCursor;
		this.totalNumber = totalNumber;
		this.hasvisible = hasvisible;
	}

	@Override
	public String toString() {
		String str = "";
		str += "StatusWapper [statuses=[";
		for (Status s : statuses) {
			str += s.toString() + " ";
		}
		str += "], ";
		str += "previousCursor=" + previousCursor + ", ";
		str += "nextCursor=" + nextCursor + ", ";
		str += "totalNumber=" + totalNumber + ", ";
		str += "hasvisible=" + hasvisible + "]";
		return str;
	}

}
