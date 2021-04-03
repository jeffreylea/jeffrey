编写dockerfile打包镜像

Dockerfile文件

```
FROM nginx:1.17.9-alpine
COPY ./html/ /usr/share/nginx/html
EXPOSE 80
```

```
#编译
docker build -t retailcloud-docker-local.repo.inspur.com/aiscreen/nginx-static:v1 .
#重新命名：
docker tag 098e5dc761a6  jeffreylea/nginx-static:v1
#传到dockerhub
docker login -u jeffreylea -p ljf3562965
docker push nginx-static:v1
```

部署yaml文件内容：

```
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: nginx-static
  namespace: ai-screen-dev
  labels:
    app: nginx-static
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: nginx-static
    spec:
      imagePullSecrets:
      - name: artifactory
      containers:
      - name: nginx-static
        image: jeffreylea/nginx-static:v1
        imagePullPolicy: Always
        ports:
        - name: tcp
          containerPort: 80
---
apiVersion: v1
kind: Service
metadata:
  name: nginx-static
  namespace: ai-screen-dev
  labels:
    app: nginx-static
spec:
  type: NodePort
  ports:
    - name: tcp
      port: 80
      targetPort: 80
  selector:
    app: nginx-static
```



