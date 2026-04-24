pipeline {
    agent any

    tools {
        jdk 'Java17' // Ensures the app is built with Java 17 tool configured in Jenkins
    }

    environment {
        // Requirement: named <your_first_name>spetitions.war
        WAR_NAME = "blessingspetitions.war"
    }

    stages {
        // i. Get the code from Github
        stage('Get Code') {
            steps {
                checkout scm
            }
        }

        // ii. Build
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        // iii. Test
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        // iv. Package and archive as a War file
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                // Rename the output to follow the naming requirement
                sh "mv target/*.war target/${WAR_NAME}"
                archiveArtifacts artifacts: "target/${WAR_NAME}", fingerprint: true
            }
        }

        // v. Deploy (Manual Approval Stage Removed for Automation)
        stage('Deploy') {
            steps {
                // Ensure the docker socket is accessible: sudo chmod 666 /var/run/docker.sock
                sh 'docker stop blessings-app || true'
                sh 'docker rm blessings-app || true'

                // Run Tomcat on Port 9090 as required
                sh 'docker run -d -p 9090:8080 --name blessings-app tomcat:9.0'

                // Deploy the specifically named WAR file
                sh "docker cp target/${WAR_NAME} blessings-app:/usr/local/tomcat/webapps/"
            }
        }
    }
}
