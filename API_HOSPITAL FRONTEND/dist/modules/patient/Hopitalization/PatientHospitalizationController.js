"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function PatientHospitalizationController($scope, $http, $routeParams, $location) {
    $scope.patient = null;
    $scope.specialties = [];
    $scope.cdSpecialty = '';
    // Buscar paciente
    $http.get('http://localhost:8080/patients/' + $routeParams.id)
        .then(function (response) {
        $scope.patient = response.data;
    });
    $scope.listSpecialties = function () {
        $http.get('http://localhost:8080/specialties')
            .then(function (response) {
            $scope.specialties = response.data;
            console.log(response.data);
        });
    };
    $scope.changeSpecialty = function (cdSpecialty) {
        $scope.cdSpecialty = cdSpecialty;
    };
    $scope.hospitalization = function () {
        $scope.errorMessage = '';
        console.log('cdSpecialty:', $scope.cdSpecialty);
        $http.post('http://localhost:8080/hospitalizations/' + $scope.patient.cdPatient + '/' + $scope.cdSpecialty, {})
            .then(function () {
            $location.path('/patient');
        }, function (error) {
            if (error.status === 404 && error.data) {
                $scope.errorMessage = error.data;
            }
            else {
                $scope.errorMessage = 'Erro ao internar paciente!';
            }
        });
    };
    $scope.internar = function () {
        $scope.errorMessage = '';
        console.log('cdSpecialty:', $scope.cdSpecialty);
        $http.post('http://localhost:8080/hospitalizations/' + $scope.patient.cdPatient + '/' + $scope.cdSpecialty, {})
            .then(function () {
            $location.path('/patient');
        }, function (error) {
            if (error.status === 404 && error.data) {
                $scope.errorMessage = error.data;
            }
            else {
                $scope.errorMessage = 'Erro ao internar paciente!';
            }
        });
    };
    $scope.voltar = function () {
        $location.path('/patient');
    };
    $scope.listSpecialties();
}
exports.default = PatientHospitalizationController;
