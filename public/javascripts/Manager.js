app.controller('manager', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {

    $scope.users = [];

    $scope.currentUser = {
        "password": "",
        "permissionManager": "",
        "userId": "",
        "username": "",
        "permissionView": "",
        "telephone": "5",
        "email": ""
    }
    // Get information conserning users
    $http.get("/GET_USERS/" + ShamayimFunctions.getCookie("username"))
        .then(function successCallback(response) {
                angular.forEach(response.data, function (value, key) {
                    $scope.users.push(value);
                }, $scope.users);
            },
            function error(response) {
                ShamayimFunctions.showAlert("Your attention please", response.data, "cant load users");
            });

    $scope.editUser = function (usr) {
        $scope.currentUser = usr;
        // In the case that is an exsiting user so the user-id is not '-1'
        // becaouse just when i add a new user the user-id is '-1'
        if (usr.userId != '-1') {
            //update users
            var res = $http.post('/UPDATE_USER', usr);
            res.success(function (data, status, headers, config) {
                alert(data);
            });
            res.error(function (data, status, headers, config) {
                alert("failure message: " + JSON.stringify({
                        data: data
                    }));
            });
        } else {
            //add users
            var res = $http.post('/ADD_USER', usr);
            res.success(function (data, status, headers, config) {
                alert(data);
            });
            res.error(function (data, status, headers, config) {
                alert("failure message: " + JSON.stringify({
                        data: data
                    }));
            });
        }

    }
    $scope.addUser = function () {
        $scope.currentUser = {
            "password": "",
            "permissionManager": "false",
            "userId": "-1",
            "username": "",
            "permissionView": "false",
            "telephone": "",
            "email": ""
        };
        $scope.users.push($scope.currentUser)
    }
    $scope.deleteUser = function (usr) {
        var index = $scope.users.indexOf(usr);
        $scope.users.splice(index, 1);
        var res = $http.post('/DELETE_USER', usr);
        res.success(function (data, status, headers, config) {
            alert(data);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                    data: data
                }));
        });

    }

   $scope.PermissionedToView ={ "PermissionedToView": [
                              	{ "UserName":"Y.Nathan", "ListOfHouses" : [
                              		{ "HouseId":"1","HouseAddress":"Newark_N.J_Prince_283"},
                              		{ "HouseId":"2","HouseAddress":"Newark_N.J_13th_718"},
                              		{ "HouseId":"3","HouseAddress":"israel_jerusalem_yirmiyahu_32"}]},
                              	{ "UserName":"Nikol", "ListOfHouses" : [
                              		{ "HouseId":"1","HouseAddress":"Newark_N.J_Prince_283"},
                              		{ "HouseId":"4","HouseAddress":"france_neuilly sur seine_delabordère_11"}]
                              	}]
                              };

    // For the ptv
    $scope.PermissionedToView = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            permited: 'default'
        }
    };
    function check()
    {


    var a = 4;
    var PermissionedToView = { "PermissionedToView": [
     	{ "UserName":"Y.Nathan", "ListOfHouses" : [
     		{ "HouseId":"1","HouseAddress":"Newark_N.J_Prince_283"},
     		{ "HouseId":"2","HouseAddress":"Newark_N.J_13th_718"},
     		{ "HouseId":"3","HouseAddress":"israel_jerusalem_yirmiyahu_32"}]},
     	{ "UserName":"Nikol", "ListOfHouses" : [
     		{ "HouseId":"1","HouseAddress":"Newark_N.J_Prince_283"},
     		{ "HouseId":"4","HouseAddress":"france_neuilly sur seine_delabordère_11"}]
     	}]
     };

        // Get information conserning the
            $http.get("/GET_PERMITED_TO_VIEW_LIST")
                .then(function successCallback(response) {
                        angular.forEach(response.data.PermissionedToView, function (value, key) {
                            itemName = {
                                id: key,
                                permited: value
                            }
                             $scope.PermissionedToView.availableOptions.push(itemName.house);
                        }, $scope.PermissionedToView);
                    },
                    function error(response) {
                        ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                    });
    }
    $interval(check(),20000);
}]);
