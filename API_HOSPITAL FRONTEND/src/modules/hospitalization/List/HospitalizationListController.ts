export default function HospitalizationListController($scope: any, $http: any, $location: any) {
  $scope.hospitalizationsList = [];

    $http.get(`http://localhost:8080/hospitalizationsActive`)
        .then(function(response: any) {
        $scope.hospitalizationsList = response.data;
    });

    $scope.voltar = function() {
        $location.path('/home');
    };
}

 