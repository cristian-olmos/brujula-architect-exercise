pipeline {

    environment {
        APP_NAME = "searcher-api.war"
    }

    agent any
    stages {
        stage('Checkout') {
                steps{
                    checkout scm
                }
        }
        stage('Compile') {
            steps{
                sh 'mvn clean install'
            }
        }
		stage("build & SonarQube analysis") {
			steps {
				withSonarQubeEnv('SonarQube-DC') {
					sh 'mvn sonar:sonar -Dsonar.jacoco.reportPaths=target/coverage-reports/jacoco-unit.exec'
				}
			}
		}
		stage("Quality Gate") {
			steps {
				timeout(time: 5, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
				}
			}
		}
        stage('Deploy') {
            steps{
                echo 'Deploying...'
            }
        }
    }
}
