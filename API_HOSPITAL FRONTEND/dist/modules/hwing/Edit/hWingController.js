"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function hWingController($scope, $http, $routeParams, $location) {
    $scope.hwing = null;
    $scope.specialties = [];
    // Buscar especialidades
    $http.get('http://localhost:8080/specialties')
        .then(function (response) {
        $scope.specialties = response.data;
    });
    // Buscar ala pelo ID
    $http.get('http://localhost:8080/hwings/' + $routeParams.id)
        .then(function (response) {
        $scope.hwing = response.data;
        // Se vier como objeto, pode ser necessário ajustar para o valor do tablist:
        // $scope.hwing.cdSpecialty = $scope.hwing.deSpecialty.cdSpecialty;
    });
    // Salvar edição
    $scope.salvarEdicao = function () {
        // Envie o cdSpecialty selecionado (ajuste conforme seu DTO)
        var dto = {
            cdSpecialty: $scope.hwing.cdSpecialty,
            cdHospital: $scope.hwing.cdHospital.cdHospital || $scope.hwing.cdHospital,
            nuRoom: $scope.hwing.nuRoom,
            nuBed: $scope.hwing.nuBed
        };
        $http.put('http://localhost:8080/hwings/' + $scope.hwing.cdHWing, dto)
            .then(function () {
            $location.path('/hwings');
        }, function () {
            alert('Erro ao editar ala!');
        });
    };
    // Voltar sem salvar
    $scope.voltar = function () {
        $location.path('/hwings');
    };
    // Selecionar especialidade pelo tablist
    $scope.selecionarEspecialidade = function (cdSpecialty) {
        $scope.hwing.cdSpecialty = cdSpecialty;
    };
}
exports.default = hWingController;
