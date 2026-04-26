pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    environment {
        WAR_NAME = "blessingspetitions.war"
    }

    stages {
        stage('Get Code') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }


        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'

                sh "mv target/*.war target/${WAR_NAME}"
                archiveArtifacts artifacts: "target/${WAR_NAME}", fingerprint: true
            }
        }

        stage('Deploy') {
            steps {

                sh 'docker stop blessings-app || true'
                sh 'docker rm blessings-app || true'

                sh 'docker run -d -p 9090:8080 --name blessings-app tomcat:9.0'

                sh "docker cp target/${WAR_NAME} blessings-app:/usr/local/tomcat/webapps/"
            }
        }
    }
}
