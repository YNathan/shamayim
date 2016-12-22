/**
 * Created by Yaacov on 29/12/2015.
 */
app = angular.module('root', ['ui.router', 'ngMaterial', 'ngMessages', 'lfNgMdFileInput', 'ShamayimService']);
app.config(function ($stateProvider, $urlRouterProvider, $mdIconProvider, $mdToastProvider) {
    $mdIconProvider.defaultIconSet('./svg/avatars.svg', 128);
    $mdIconProvider.icon('share', './svg/share.svg', 24);
    $mdIconProvider.icon('menu', './svg/menu.svg', 24);
    $mdIconProvider.icon('account_circle', './svg/account_circle.svg');
    $mdIconProvider.icon('attach_money', './svg/attach_money.svg');
    $mdIconProvider.icon('copyright', './svg/copyright.svg');
    $mdIconProvider.icon('home', './svg/home.svg');


    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/wellcom");
    //
    // Now set up the states
    $stateProvider
        .state('wellcom', {
            url: "/wellcom",
            templateUrl: "template/LoginRegister.html",
            controller: 'loginRegister'
        })
        .state('userInformation', {
            url: "/userInformation",
            templateUrl: "template/userInformation.html",
            controller: 'userInformation'
        })
        .state('Houses', {
            url: "/houses",
            templateUrl: "template/Houses.html",
            controller: 'houses'
        })
        .state('House', {
            url: "/house",
            templateUrl: "template/House.html",
            controller: 'house'
        })
        .state('NewOrEditHouse', {
            url: "/neworedithouse",
            templateUrl: "template/NewOrEditHouse.html",
            controller: 'neworedithouse'
        })
        .state('Copyright', {
            url: "/Copyright",
            templateUrl: "template/Copyright.html",
            controller: 'Copyright'
        });
});
