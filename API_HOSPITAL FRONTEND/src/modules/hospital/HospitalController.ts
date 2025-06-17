export default function HospitalController($scope: any) {
  $scope.hospitais = [];
  $scope.newHospital = '';
  
  $scope.addHospital = function () {
    if ($scope.newHospital) {
      $scope.hospitais.push($scope.newHospital);
      $scope.newHospital = '';
    }
  };

  $scope.listHospitais = function () {
    // Aqui você pode implementar busca em uma API futuramente
  };
}