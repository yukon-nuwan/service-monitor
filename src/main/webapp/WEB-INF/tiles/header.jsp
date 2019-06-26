<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<nav id="menuNav" class="navbar navbar-expand-lg navbar-dark bg-primary ">
    <a class="navbar-brand" href="#">Monitor</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/caller">Register Caller</a>
                    <a class="dropdown-item" href="/service">Register Service</a>
                    <a class="dropdown-item" href="/callerService">Subscribe Service</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/home">Dashboard</a>
                </div>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<!--Model Popup starts-->
<div class="modal fade" id="infoModal" role="dialog">
      <div class="modal-dialog" id="infoDialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label=""><span>×</span></button>
               </div>

           		<div class="modal-body">
					<div id="infoPopContent" class="info-pop">
						<h1>Success !</h1>
					</div>
                   
             	</div>
          </div>
      </div>
  </div>

<script>
   
</script>
