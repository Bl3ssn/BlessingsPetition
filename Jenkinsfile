pipeline {
    agent any

    environment {
        app_name = "blessingspetitions.war"
    }

    stages {
        stage('Get Code') {
            steps {
                echo 'copy code from GitHub'
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
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                echo "Starting deployment..."
                sh "sudo cp target/${app_name} /opt/tomcat/webapps/"
            }
        }
    }

        post {
             success {
                        echo 'Pipeline successful!'
             }
             failure {
                        echo 'Pipeline failed!'
            }
       }

}
