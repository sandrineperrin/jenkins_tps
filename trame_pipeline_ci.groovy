node {
  

  stage('clean'){
    print "Remove git clone"
    deleteDir()
    checkout scm
  }

  stage('Context'){
    print "Affichage information sur l'environnement d'execution"
    print "Var : JAVA_HOME, JAVA_VERSION, MVN_VERSION"
    sh "echo $JAVA_HOME"
    sh "mvn --version"
  }

  stage('UnitTest'){
    # Run unitTest with mvn
    # recupérer les rapports générés pour affichage
  }

  stage('Build'){
    # Build artefact : mvn command
  }

  stage('push'){
    # Push artefact on Nexus: ansible playbook
  }

  stage('Deploy_env'){
    # Call dedicate job
     build job 'trc/deploy'
          parameters: [
            string(name: 'environment', value: $params.environment),
            booleanParam(name: 'snapshot', value: $params.snapshot),
            credentials(description: 'description', name: 'nexus_credential', value: $NEXUS_ACCES)
          ]
  }
  
  stage('TF'){
    # Call dedicate job
     build job 'trc/katalon_trc'
  }
  
  stage('TestPerf'){
    build job 'trc/jmeter_trc'
  }

  stage('notification_success'){

  }
}
