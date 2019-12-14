/*
* Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.learn.jeffrey.snowflake;

/**
 * 
 * <p>自定义ID生成异常类</p>
 * @author lijianfei
 * @date 2019-12-14
 */
public class GenerateIdException extends RuntimeException {

   /**
    * Serial Version UID
    */
   private static final long serialVersionUID = -27048199131316992L;

   /**
    * Default constructor
    */
   public GenerateIdException() {
       super();
   }

   /**
    * Constructor with message & cause
    * 
    * @param message
    * @param cause
    */
   public GenerateIdException(String message, Throwable cause) {
       super(message, cause);
   }

   /**
    * Constructor with message
    * 
    * @param message
    */
   public GenerateIdException(String message) {
       super(message);
   }
   
   /**
    * Constructor with message format
    * 
    * @param msgFormat
    * @param args
    */
   public GenerateIdException(String msgFormat, Object... args) {
       super(String.format(msgFormat, args));
   }

   /**
    * Constructor with cause
    * 
    * @param cause
    */
   public GenerateIdException(Throwable cause) {
       super(cause);
   }

}