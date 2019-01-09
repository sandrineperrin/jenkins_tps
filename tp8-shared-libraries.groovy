#!/usr/bin/env groovy

def call(status){

    println status + ": Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':"
    println "Check console output at ${env.BUILD_URL} ${env.JOB_NAME} [${env.BUILD_NUMBER}]"
}
