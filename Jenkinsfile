#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        java -version
    }

    stage('clean') {
        ./gradlew clean --no-daemon
    }

    stage('npm install') {
        ./gradlew npm_install -PnodeInstall --no-daemon
    }

    stage('backend tests') {
        try {
            ./gradlew test integrationTest -PnodeInstall --no-daemon
        } catch(err) {
            throw err
        } finally {
            junit '**/build/**/TEST-*.xml'
        }
    }

    stage('frontend tests') {
        try {
            ./gradlew npm_run_test -PnodeInstall --no-daemon
        } catch(err) {
            throw err
        } finally {
            junit '**/build/test-results/TESTS-*.xml'
        }
    }

    stage('packaging') {
        ./gradlew bootJar -x test -Pprod -PnodeInstall --no-daemon
        archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
    }

}
