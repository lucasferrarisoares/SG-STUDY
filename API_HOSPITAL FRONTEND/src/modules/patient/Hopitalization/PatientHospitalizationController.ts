export default function PatientHospitalizationController($scope: any, $http: any, $routeParams: any, $location: any) {
  $scope.patient = null;
  $scope.specialties = [];
  $scope.cdSpecialty = '';


  // Buscar paciente
  $http.get('http://localhost:8080/patients/' + $routeParams.id)
    .then(function(response: any) {
      $scope.patient = response.data;
    });

  $scope.listSpecialties = function () {
    $http.get('http://localhost:8080/specialties')
      .then(function(response: any) {
        $scope.specialties = response.data;
        console.log(response.data);
      });
  };

  $scope.changeSpecialty = function (cdSpecialty: number) {
    $scope.cdSpecialty = cdSpecialty;
  };

  $scope.hospitalizationPatient = function() {
    $scope.errorMessage = '';
    console.log('cdSpecialty:', $scope.cdSpecialty);
    $http.post('http://localhost:8080/hospitalizations/' + $scope.patient.cdPatient + '/' + $scope.cdSpecialty, {})
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

  $scope.internar = function() {
    $scope.errorMessage = '';
    console.log('cdSpecialty:', $scope.cdSpecialty);
    $http.post('http://localhost:8080/hospitalizations/' + $scope.patient.cdPatient + '/' + $scope.cdSpecialty, {})
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

  $scope.voltar = function() {
    $location.path('/patient');
  };

  $scope.listSpecialties();
}