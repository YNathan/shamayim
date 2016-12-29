app.controller('manager', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {

$scope.users = [];

    // Get information conserning the
       $http.get("/GET_USERS/"+ShamayimFunctions.getCookie("username"))
           .then(function successCallback(response) {
                   angular.forEach(response.data, function (value, key) {
                      $scope.users.push(value);
                   }, $scope.users);
               },
               function error(response) {
                   ShamayimFunctions.showAlert("Your attention please", response.data, "cant load users");
               });

    $scope.editUser = function(usr)
    {
    alert(usr.userId );
    }
}]);
