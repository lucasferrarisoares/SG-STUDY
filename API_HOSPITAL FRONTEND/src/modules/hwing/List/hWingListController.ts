export default function hWingController($scope: any, $http: any, $location: any) {
  $scope.hospitals = [];
  $scope.wings = [];
  $scope.newHwing = {};
  $scope.specialties = [];

  $scope.listhWing = function () {
    $http.get('http://localhost:8080/hwings')
      .then(function(response: any) {
        $scope.wings = response.data;
      });
  };

  $scope.addhWing = function () {
  if ($scope.newHwing.cdSpecialty && $scope.newHwing.cdHospital) {
    $http.post('http://localhost:8080/hwings', $scope.newHwing)
      .then(function() {
        $scope.newHwing = { cdSpecialty: '', cdHospital: '', nuRoom: '', nuBed: '' };
        $scope.listhWing();
      });
  }
  };

  $scope.removehWing = function(cdHWing: number) {
    $http.delete('http://localhost:8080/hwings/' + cdHWing)
      .then(function() {
        $scope.listhWing();
      });
  };

  $scope.edithWing = function(cdHWing: number) {
    $location.path('/hwing/' + cdHWing + '/editar');
  };

  $scope.listHospitals = function () {
    $http.get('http://localhost:8080/hospitals')
      .then(function(response: any) {
        $scope.hospitals = response.data;
      });
  }

  $scope.listSpecialties = function () {
    $http.get('http://localhost:8080/specialties')
      .then(function(response: any) {
        $scope.specialties = response.data;
      });
  };

  // Inicialização
  $scope.listSpecialties();
  $scope.listHospitals();
  $scope.listhWing();
}