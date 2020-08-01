import Helpers.ArtifactoryRepositoryHelper

pipeline {
 agent any
 environment {
  dotnet = "$buildpath"
 }
 stages {
  stage('Initialize') {
   steps {
        def artifactoryreponame = //Read from Yaml
        def giturl
        def $branchname
    }
  }   
  stage('Checkout') {
   steps {
    git credentialsId: "$gitcredencialId", url: "$giturl", branch: "$branchname"
   }
  }
  stage('Restore PACKAGES') {
   steps {
    bat "dotnet restore --configfile NuGet.Config"
   }
  }
  stage('Clean') {
   steps {
    bat 'dotnet clean'
   }
  }
  stage('Build') {
   steps {
    bat 'dotnet build --configuration Release'
   }
  }
  stage('Pack') {
   steps {
    bat 'dotnet pack --no-build --output nupkgs'
   }
  }
  stage('Upload') {
   steps {
    ArtifactoryRepositoryHelper.Upload( reponame: "$artifactoryreponame")
   }
 }
}