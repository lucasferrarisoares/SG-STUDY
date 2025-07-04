export default function PatientListController($scope: any, $http: any, $location: any) {
  $scope.patients = [];
  $scope.newPatient = { dePatient: '' };

  $scope.listPatients = function () {
    $http.get('http://localhost:8080/patients')
      .then(function(response: any) {
        $scope.patients = response.data;
      });
  };

  $scope.addPatient = function () {
    if ($scope.newPatient.dePatient) {
      $http.post('http://localhost:8080/patients', $scope.newPatient)
        .then(function() {
          $scope.newPatient = { dePatient: '' };
          $scope.listPatients();
        });
    }
  };

  $scope.removePatient = function(cdPatient: number) {
    $http.delete('http://localhost:8080/patients/' + cdPatient)
      .then(function() {
        $scope.listPatients();
      });
  };

  $scope.editPatient = function(cdPatient: number) {
    $location.path('/patient/' + cdPatient + '/editar');
  };

  $scope.hospitalization = function(cdPatient: number) {
    $location.path('/patients/' + cdPatient + '/internar');
  };

  $scope.listPatients();
}