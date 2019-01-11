//
// 1 - Variable d'environnement
//
node {

  stage('0- clean'){
    deleteDir()
    checkout scm
  }

  stage ('1- Print Jenskins variables'){
    echo "$env.VAR_GLOBAL"
  }
  stage ('1- Print all env'){
    echo 'Affiche toutes les variables environnement disponibles :'
    sh 'env'
  }
}

//
// 2 - Paramètres utilisateurs du script
//

node {
  stage('2- Print parameter'){
    sh '''
       echo "Affichage du paramètre saisie par l'utilisateur"
       echo "  valeur du paramètre : $missing_param"
    '''

    // Pour récupérer la valeur dans le script
    def value = params.var_param
    def value_upper = params.var_param.toUpperCase()

    println "Print default => " + params.var_param
    println "Print default => " + value
    println "Print upper case value => $value_upper"

  }
}

//
// 3 - Credentials
//

node {
  stage('3- Récupération des crédentials'){
    withCredentials([
      usernamePassword(
          credentialsId: 'github-acces',
          usernameVariable: "DEMO_USERNAME",
          passwordVariable: "DEMO_PASS"
      ),
      file(
        credentialsId: '0f8d64e9-73f7-454f-8aa1-2ead1fee55b1',
        variable: 'SECRET_FILE')
    ]){

      // Affichage des variables :
      print "User $DEMO_USERNAME password $DEMO_PASS"
      print "Path du secret file $SECRET_FILE"
      sh "cp $SECRET_FILE $WORKSPACE"
      sh 'ls -l'
      
    }
  } // end withCredential, les variables ne sont plus accessibles après
}
