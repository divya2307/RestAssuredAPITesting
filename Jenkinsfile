pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9.14'
        jdk 'JDK21'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling code from GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building project...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running Cucumber tests...'
                sh 'mvn test'
            }
        }
        
        stage('Reports') {
            steps {
                echo 'Publishing reports...'
                publishHTML([
                    reportDir: 'target/cucumber-report.html',
                    reportFiles: 'index.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }
    
    post {
        success {
            echo '✅ Build Successful!'
        }
        failure {
            echo '❌ Build Failed!'
        }
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}