export default function hWingController($scope: any, $http: any, $routeParams: any, $location: any) {
  $scope.hwing = null;
  $scope.specialties = [];

  $http.get('http://localhost:8080/specialties')
    .then(function(response: any) {
      $scope.specialties = response.data;
    });

  $http.get('http://localhost:8080/hwings/' + $routeParams.id)
    .then(function(response: any) {
      $scope.hwing = response.data;
    });

  $scope.salvarEdicao = function() {
    const dto = {
      cdSpecialty: $scope.hwing.cdSpecialty,
      cdHospital: $scope.hwing.cdHospital.cdHospital || $scope.hwing.cdHospital, // ajuste conforme backend
      nuRoom: $scope.hwing.nuRoom,
      nuBed: $scope.hwing.nuBed
    };
    $http.put('http://localhost:8080/hwings/' + $scope.hwing.cdHWing, dto)
      .then(function() {
        $location.path('/hwings');
      }, function() {
        alert('Erro ao editar ala!');
      });
  };

  // Voltar sem salvar
  $scope.voltar = function() {
    $location.path('/hwings');
  };

  // Selecionar especialidade pelo tablist
  $scope.selecionarEspecialidade = function(cdSpecialty) {
    $scope.hwing.cdSpecialty = cdSpecialty;
  };
}