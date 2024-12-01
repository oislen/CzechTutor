# get base image
FROM ubuntu:latest

# set environment variables
ENV user=ubuntu
ENV DEBIAN_FRONTEND=noninteractive
# set git branch for cloning
ARG GIT_BRANCH
ENV GIT_BRANCH=${GIT_BRANCH}

# install required software and programmes for development environment
RUN apt-get update 
RUN apt-get install -y apt-utils vim curl wget unzip git 

# install java
RUN wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie" https://download.oracle.com/java/23/latest/jdk-23_linux-x64_bin.tar.gz
RUN tar -xvf jdk-23_linux-x64_bin.tar.gz
RUN mv jdk-23.0.1 /opt/
RUN rm jdk-23_linux-x64_bin.tar.gz
# install maven
RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz -P /tmp
RUN tar xf /tmp/apache-maven-*.tar.gz -C /opt
RUN ln -s /opt/apache-maven-3.9.9 /opt/maven
# set global environment path and variables
ENV JAVA_HOME=/opt/jdk-23.0.1
ENV M2_HOME=/opt/maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=$PATH:/opt/jdk-23.0.1/bin/
ENV PATH=${M2_HOME}/bin:${PATH}

# set up home environment
RUN mkdir -p /home/${user} && chown -R ${user}: /home/${user}

# clone cat-classifier git repo
#RUN git clone https://github.com/oislen/CzechTutor.git --branch ${GIT_BRANCH} /home/${user}/CzechTutor
COPY . /home/${user}/CzechTutor

WORKDIR /home/${user}/CzechTutor
# compile maven spring boot project
RUN mvn compile
CMD ["mvn", "spring-boot:run"]