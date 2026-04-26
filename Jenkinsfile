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

         stage('Approve Deploy') {
             steps {
                input message: 'Do you want to deploy?', ok: 'Deploy'
                echo "moving to deployment stage"
             }
         }

        stage('Deploy') {
            steps {
                echo "Starting deployment..."
                sh '''
                    sudo systemctl stop tomcat
                    sudo rm -rf /opt/tomcat/webapps/blessingspetitions
                    sudo rm -f /opt/tomcat/webapps/blessingspetitions.war
                    sudo cp target/blessingspetitions.war /opt/tomcat/webapps/
                    sudo systemctl start tomcat
                '''
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
