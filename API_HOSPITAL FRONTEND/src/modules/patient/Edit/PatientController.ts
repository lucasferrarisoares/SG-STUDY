export default function PatientController($scope: any, $http: any, $routeParams: any, $location: any) {
  $scope.patient = null;

  $http.get('http://localhost:8080/patients/' + $routeParams.id)
    .then(function(response: any) {
      $scope.patient = response.data;
    });

  $scope.saveEdicao = function() {
    $http.put('http://localhost:8080/patients/' + $scope.patient.cdPatient, 
      { dePatient: $scope.patient.dePatient })
      .then(function() {
        $location.path('/patient');
      }, function() {
        alert('Erro ao editar paciente!');
      });
  };

  $scope.voltar = function() {
    $location.path('/patient');
  };
}