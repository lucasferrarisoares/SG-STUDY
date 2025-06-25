export default function HospitalController($scope: any, $http: any, $routeParams: any, $location: any) {
  $scope.hospital = null;

  $http.get('http://localhost:8080/hospitals/' + $routeParams.id)
    .then(function(response: any) {
      $scope.hospital = response.data;
    });

  $scope.saveEdicao = function() {
    $http.put('http://localhost:8080/hospitals/' + $scope.hospital.cdHospital, 
      { deHospital: $scope.hospital.deHospital })
      .then(function() {
        $location.path('/hospital');
      }, function() {
        alert('Erro ao editar hospital!');
      });
  };

  $scope.voltar = function() {
    $location.path('/hospital');
  };
}