<!DOCTYPE html>
<html class="no-js" lang="">

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="manifest" href="site.webmanifest">
    <link rel="apple-touch-icon" href="icon.png">
    <!-- Place favicon.ico in the root directory -->
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/main.css">
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500&display=swap" rel="stylesheet">


    <meta name="theme-color" content="#fafafa">
</head>

<body>
<!--[if IE]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
<![endif]-->

<!-- Add your site or application content here -->
<div class="block conference">
    <div class="container">
        <h1>Conference Back Office</h1>
        <#if version??><h2>Monolith - V${version}</h2></#if>
        <h4><a href="/">Main Site</a></h4>
    </div>
</div>
<div class="bottom-blocks">
    <div class="block block-left">
        <div class="container">
            <h2>${c4p}</h2>
            <ul>
                <li>Approve/Reject Proposal (Back office)</li>
                <li>Notify Author about Results via email (Back office)</li>
            </ul>
        </div>
    </div>
    <div class="block block-right">
        <div class="container">
            <h2>${email}</h2>
            <ul>
                <li>Send Reminder Email</li>
            </ul>
        </div>
    </div>
</div>
<script src="js/vendor/modernizr-3.7.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-3.4.1.min.js"><\/script>')</script>
<script src="js/plugins.js"></script>

</body>

</html>