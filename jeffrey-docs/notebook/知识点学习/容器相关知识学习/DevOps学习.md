

 Jenkins-pipeline支持的两种书写语法格式

+ 声明式流水线（Declarative Pipeline）

  ```json
  pipeline { // pipeline是声明式流水线的一种特定语法，在块内定义了整个流水线的所有内容
      agent any //	agent是声明式流水线的一种特定语法，它指示 Jenkins 为整个流水线分配一个执行器 (在节点上)和工作区，等效于脚本式流水线node块
      stages { // 所有流程（状态）的外层块，仅有一个
          stage('Build') { //每个stage为一流程，与脚本式基本一致，每个stage可以定义名称
              steps {  //步骤块，内部包含具体操作
                  sh 'make'  // sh操作，其引号间的文字会当成shell直接执行
              }
          }
          stage('Test'){
              steps {
                  sh 'make check'
                  junit 'reports/**/*.xml'  //junit使用匹配的定义测试xml进行单元测试
              }
          }
          stage('Deploy') {
              steps {
                  sh 'make publish'
              }
          }
      }
  }
  ```

  

+ 脚本式流水线（Scripted Pipeline）

  ```json
  node { //需要有一个或多个node节点表示一系列操作
      stage('Build') { //每个stage表示一个步骤
          // Build步骤内容
      }
      stage('Test') { 
          //  Test步骤内容
      }
      stage('Deploy') { 
          //  Deploy步骤内容
      }
  }
  ```

  流水线用特殊的语句或者元素定义章节，遵循groovy语法（也是一种编程语言，和java类似，待学习）。

+ 节点块：

  首先定义的是“node”，node是jekins分布式架构的一部分，他可以把负载分布到多个“agent”节点，"master"节点处理所有的环境，Jenkins 代理节点从 master 节点上取得构建任务，然后根据 node 块指定的节点上执行所有任务。