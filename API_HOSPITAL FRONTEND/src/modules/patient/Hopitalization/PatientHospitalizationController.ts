export default function PatientHospitalizationController($scope: any, $http: any, $routeParams: any, $location: any) {
  $scope.patient = null;
  $scope.hospitalizationDetails = null;
  $scope.specialties = [];
  $scope.cdSpecialty = '';
  $scope.isHospitalized = false;


  // Buscar paciente
  $http.get('http://localhost:8080/patients/' + $routeParams.id)
    .then(function(response: any) {
      $scope.patient = response.data;
    });

  $scope.listSpecialties = function () {
    $http.get('http://localhost:8080/specialties')
      .then(function(response: any) {
        $scope.specialties = response.data;
      });
  };

  $scope.getHospitalizationDetails = function (cdPatient: number) {
    $http.get('http://localhost:8080/patientsHospitalization/' + cdPatient)
      .then(function(response: any) {
        $scope.hospitalizationDetails = response.data;
      });
  };

  $scope.changeSpecialty = function (cdSpecialty: number) {
    $scope.cdSpecialty = cdSpecialty;
  };

  $scope.hospitalizationPatient = function() {
    $scope.errorMessage = '';
    console.log('Internando paciente:', $scope.patient.cdPatient, 'Especialidade:', $scope.cdSpecialty);
    $http.post('http://localhost:8080/hospitalizations/' + $scope.patient.cdPatient + '/' + $scope.cdSpecialty)
      .then(function() {
        $location.path('/patient');
      }, function(error: any) {
        if (error.status === 404 && error.data) {
          $scope.errorMessage = error.data;
        } else {
          $scope.errorMessage = 'Erro ao internar paciente!';
        }
      });
  };

  $scope.releasePatient = function() {
    $scope.errorMessage = '';
    console.log($scope.hospitalizationDetails.cdHospitalization);
    $http.put('http://localhost:8080/releasePatient/' + $scope.hospitalizationDetails.cdHospitalization)
      .then(function() {
        $location.path('/patient');
      }, function(error: any) {
        if (error.status === 404 && error.data) {
          $scope.errorMessage = error.data;
        } else {
          $scope.errorMessage = 'Erro ao dar alta para o paciente!';
        }
      });
  };


  $scope.checkHospitalization = function() {
  $http.get('http://localhost:8080/verifyHospitalizationByPatient/' + $routeParams.id)
    .then(function(response: any) {
      $scope.isHospitalized = response.data; 
      if ($scope.isHospitalized) {
        $scope.getHospitalizationDetails($routeParams.id);
      }
    });
  };

  $scope.voltar = function() {
    $location.path('/patient');
  };

  $scope.goToLog = function() {
    $location.path('/patientlogs/' + $routeParams.id);
  };

  $scope.listSpecialties();
  $scope.checkHospitalization();
}