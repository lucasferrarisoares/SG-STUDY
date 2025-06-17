export default function hWingController($scope) {
  $scope.specialty = ['Geral', 'Pediatria', 'Ortopedia', 'Cardiologia'];
  $scope.hospitals = ['Hospital São Lucas', 'Hospital Calica', 'Hospital SG'];
  $scope.wings = [];
  $scope.newHwing = {};

  $scope.addHwing = function () {
    if ($scope.newHwing.specialty && $scope.newHwing.hospital) {
      $scope.wings.push({
        specialty: $scope.newHwing.specialty,
        hospital: $scope.newHwing.hospital
      });
      $scope.newHwing = {};
    }
  };
}