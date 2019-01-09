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
          credentialsId: 'missing_credential',
          usernameVariable: "DEMO_USERNAME",
          passwordVariable: "DEMO_PASS"
      ),
      file(
        credentialsId: 'missing_secret_file',
        variable: 'SECRET_FILE')
    ]){

      // Affichage des variables :
 
    }
  } // end withCredential, les variables ne sont plus accessibles après
}
