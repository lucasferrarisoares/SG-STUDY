export default function HospitalListController($scope: any, $http: any, $location: any) {
  $scope.hospitais = [];
  $scope.newHospital = { deHospital: '' };

  $scope.listHospitais = function () {
    $http.get('http://localhost:8080/hospitals')
      .then(function(response: any) {
        $scope.hospitais = response.data;
      });
  };

  $scope.addHospital = function () {
    if ($scope.newHospital.deHospital) {
      $http.post('http://localhost:8080/hospitals', $scope.newHospital)
        .then(function() {
          $scope.newHospital = { deHospital: '' };
          $scope.listHospitais();
        });
    }
  };

  $scope.removeHospital = function(cdHospital: number) {
    $http.delete('http://localhost:8080/hospitals/' + cdHospital)
      .then(function() {
        $scope.listHospitais();
      });
  };

  $scope.editHospital = function(cdHospital: number) {
    $location.path('/hospitais/' + cdHospital + '/editar');
  };

  $scope.listHospitais();
}