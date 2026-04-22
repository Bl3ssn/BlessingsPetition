pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Build') {
            steps { sh 'mvn clean compile' }
        }
        stage('Test') {
            steps { sh 'mvn test' }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                sh 'mv target/*.war target/blessingspetitions.war'
                archiveArtifacts artifacts: 'target/*.war'
            }
        }
        stage('Deploy') {
            steps {
                input message: 'Deploy to Tomcat on Port 9090?'
                // Simple deploy using Docker as a Tomcat container
                sh 'docker stop pet-app || true && docker rm pet-app || true'
                sh 'docker run -d -p 9090:8080 --name pet-app tomcat:9.0'
                sh 'docker cp target/blessingspetitions.war pet-app:/usr/local/tomcat/webapps/'
            }
        }
    }
}
