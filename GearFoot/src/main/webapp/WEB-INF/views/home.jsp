<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images"/>
<div class="container-fluid">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="first-slide home-image" src="${images}/c1.png" alt="first slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="second-slide home-image" src="${images}/c2.png" alt="second slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            
                            
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="third-slide home-image" src="${images}/c3.png" alt="third slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                         
                            
                        </div>
                    </div>
                </div>
               
</div>