pipeline {
    agent none
    stages {
        stage("Build for different JDKs") {
            parallel {
                stage('JDK-10') {
                    agent {
                        docker {
                            image 'maven:3.6-jdk-10'
                            args '-v /home/jenkins/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2 -e MAVEN_OPTS=-Duser.home=/var/maven'
                        }
                    }
                    steps {
                        testAgainstJdk("10")
                    }
                }
                stage('JDK-11') {
                    agent {
                        docker {
                            image 'maven:3.6-jdk-11'
                            args '-v /home/jenkins/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2 -e MAVEN_OPTS=-Duser.home=/var/maven'
                        }
                    }
                    steps {
                        testAgainstJdk("11")
                    }
                }
                stage('JDK-12') {
                    agent {
                        docker {
                            image 'maven:3.6-jdk-12'
                            args '-v /home/jenkins/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2 -e MAVEN_OPTS=-Duser.home=/var/maven'
                        }
                    }
                    steps {
                        testAgainstJdk("12")
                    }
                }
                stage('JDK-13') {
                    agent {
                        docker {
                            image 'maven:3.6-jdk-13'
                            args '-v /home/jenkins/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2 -e MAVEN_OPTS=-Duser.home=/var/maven'
                        }
                    }
                    steps {
                        testAgainstJdk("13")
                    }
                }
            }
        }
    }
}

void testAgainstJdk(version) {
    stage("Show Versions") {
        script {
            sh 'mvn -version'
            sh 'java -version'
            sh 'javac -version'
        }
    }

    stage("Clean Maven Project") {
        script {
            sh 'mvn clean -Dmaven.clean.failOnError=false -Dmaven.clean.retryOnError=true'
        }
    }

    stage("Test against JDK $version") {
        script {
            sh "mvn integration-test -Dmaven.compiler.release=$version"
        }
    }
}
