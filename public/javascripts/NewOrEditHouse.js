app.controller('neworedithouse', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {
    var houseId = -1;
    $scope.files;
    $scope.houseProfilePicture;
    $scope.houseDocuments;
    $scope.strCaptionDragAndDrop = "Drag & drop files here...";
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + ShamayimFunctions.getCookie("username");
    $scope.DebterName = "";
    $scope.house_id = "1";
    $scope.state = "israel";
    $scope.city = "jerusalem";
    $scope.street = "yirmiyahu";
    $scope.house_number = 32;
    $scope.house_kind = 3;
    $scope.number_of_rooms = 1;
    $scope.number_of_living_rooms = 1;
    $scope.number_of_kitchens = 1;
    $scope.number_of_bedrooms = 3;
    $scope.number_of_bathrooms = 5;
    $scope.location_kind = 5;
    $scope.comments = "nice area have fun";
    $scope.purchase_price = 0.0;
    $scope.treatment_fees = 0.0;
    $scope.divers_fees = 0.0;
    $scope.renovation_fees_for_sale = 0.0;
    $scope.renovation_fees_for_renting = 0.0;
    $scope.userName = ShamayimFunctions.getCookie("username");
    var houseName = "";
    var tempArr = [];
    var house = {
        "houseId": "-1",
        "state": "israel",
        "city": "jerusalem",
        "street": "yirmiyahu",
        "house_number": "32",
        "house_kind": "1",
        "number_of_rooms": "3",
        "number_of_living_rooms": "1",
        "number_of_kitchens": "2",
        "number_of_bedrooms": "1",
        "number_of_bathrooms": "1",
        "location_kind": "dfg",
        "comments": "Nice area great place to have fun",
        "purchase_price": "0.0",
        "treatment_fees": "0.0",
        "renovation_fees": "0.0",
        "divers_fees": "0.0",
        "renovation_fees_for_renting": "0.0",
        "renovation_fees_for_sale": "0.0"
    }

    $scope.dataTabs = {
        selectedIndex: 0,
        TabsLocked: true,
        bottom: false
    };
    $scope.next = function () {
        $scope.dataTabs.selectedIndex = Math.min($scope.dataTabs.selectedIndex + 1, 2);
    };
    $scope.previous = function () {
        $scope.dataTabs.selectedIndex = Math.max($scope.dataTabs.selectedIndex - 1, 0);
    };


    // Get information conserning the
    $scope.setNewHouse = function () {
        house.state = $scope.state;
        house.city = $scope.city;
        house.street = $scope.street;
        house.house_number = $scope.house_number;
        house.house_kind = $scope.house_kind;
        house.number_of_rooms = $scope.number_of_rooms;
        house.number_of_living_rooms = $scope.number_of_living_rooms;
        house.number_of_kitchens = $scope.number_of_kitchens;
        house.number_of_bedrooms = $scope.number_of_bedrooms;
        house.number_of_bathrooms = $scope.number_of_bathrooms;
        house.location_kind = $scope.location_kind;
        house.comments = $scope.comments;


        var res = $http.post('/SET_NEW_HOUSE', house);
        res.success(function (data, status, headers, config) {
            $scope.dataTabs.TabsLocked = false;
            alert(data);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                    data: data
                }));
        });


    }

    $scope.setNewHouseAddress = function () {
        house.state = $scope.state;
        house.city = $scope.city;
        house.street = $scope.street;
        house.house_number = $scope.house_number;
        house.house_kind = $scope.house_kind;


        var res = $http.post('/SET_NEW_HOUSE_ADDRESS', house);
        res.success(function (data, status, headers, config) {
            $scope.dataTabs.TabsLocked = false;
            alert(data.WebResponce[0].Reason);
            houseId = data.WebResponce[0].MoreDetails;
            alert(houseId);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                    data: data
                }));
        });


    }

    $scope.setHouseGeneralDetails = function () {
        house.houseId = houseId;
        house.house_kind = $scope.house_kind;
        house.number_of_rooms = $scope.number_of_rooms;
        house.number_of_living_rooms = $scope.number_of_living_rooms;
        house.number_of_kitchens = $scope.number_of_kitchens;
        house.number_of_bedrooms = $scope.number_of_bedrooms;
        house.number_of_bathrooms = $scope.number_of_bathrooms;
        house.location_kind = $scope.location_kind;
        house.comments = $scope.comments;


        var res = $http.post('/SET_HOUSE_GENERAL_DETAILS', house);
        res.success(function (data, status, headers, config) {
            $scope.dataTabs.TabsLocked = false;
            alert(data.WebResponce[0].Reason);
            alert(houseId);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                    data: data
                }));
        });


    }

    $scope.setHouseFinancialDetails = function () {
        house.houseId = houseId;
        house.purchase_price = $scope.purchase_price;
        house.treatment_fees = $scope.treatment_fees;
        house.renovation_fees_for_sale = $scope.renovation_fees_for_sale;
        house.renovation_fees_for_renting = $scope.renovation_fees_for_renting;
        house.divers_fees = $scope.divers_fees;


        var res = $http.post('/SET_HOUSE_FINANCIAL_DETAILS', house);
        res.success(function (data, status, headers, config) {
            $scope.dataTabs.TabsLocked = false;
            alert(data.WebResponce[0].Reason);
            alert(houseId);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                    data: data
                }));
        });


    }


    // Just check if there is a user name
    if (ShamayimFunctions.getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }


    // House Profile Pictures Section
    $scope.$watch('houseProfilePicture.length', function (newVal, oldVal) {
      console.log($scope.houseProfilePicture);
    });

    // Upload File To The Server (House Pictures OR Some Else File)
    $scope.uploadHouseProfilePicture = function () {
        var formData = new FormData();
        angular.forEach($scope.houseProfilePicture, function (obj) {
            formData.append('files[]', obj.lfFile);
        });

        $http.post("/SET_HOUSE_PROFILE_PICTURE/" + house.state + "_" + house.city + "_" + house.street + "_" + house.house_number, formData, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            alert("Yeah!!!")
        ).error(
            alert("Oups! something wrong was hapening")
        );
    }

    // House Pictures Section
    $scope.$watch('files.length', function (newVal, oldVal) {
        console.log($scope.files);
    });

    // Upload File To The Server (House Pictures OR Some Else File)
    $scope.uploadHousePictures = function () {
        var formData = new FormData();
        angular.forEach($scope.files, function (obj) {
            formData.append('files[]', obj.lfFile);
        });

        $http.post("/SET_HOUSE_PICTURES/" + house.state + "_" + house.city + "_" + house.street + "_" + house.house_number, formData, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            alert("Yeah!!!")
        ).error(
            alert("Oups! something wrong was hapening")
        );
    }


    // House Documents Section
    $scope.$watch('houseDocuments.length', function (newVal, oldVal) {
        console.log($scope.houseDocuments);
    });

    // Upload File To The Server (House Pictures OR Some Else File)
    $scope.uploadFiles = function () {
        var formData = new FormData();
        angular.forEach($scope.houseDocuments, function (obj) {
            formData.append('files[]', obj.lfFile);
        });

        $http.post("/SET_HOUSE_DOCUMENTS/" + house.state + "_" + house.city + "_" + house.street + "_" + house.house_number, formData, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            alert("Yeah!!!")
        ).error(
            alert("Oups! something wrong was hapening")
        );
    }

}]);
