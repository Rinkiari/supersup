<script>
export default {
  mounted() {
    const sidebar = document.querySelector(".left-section");
    const sidebarItems = document.querySelectorAll(".sidebar .item");
    const catItems = document.querySelectorAll(".categories a");

    sidebarItems.forEach((sideItem) => {
      sideItem.addEventListener("click", () => {
        sidebarItems.forEach((item) => {
          item.classList.remove("active");
        });
        sideItem.classList.add("active");
      });
    });

    catItems.forEach((catItem) => {
      catItem.addEventListener("click", () => {
        catItems.forEach((item) => {
          item.classList.remove("active");
        });
        catItem.classList.add("active");
      });
    });

    window.addEventListener("scroll", () => {
      if (window.innerWidth <= 992) {
        if (this.pageYOffset > 20) {
          sidebar.style.paddingTop = "20px";
        } else {
          sidebar.style.paddingTop = "70px";
        }
      }
    });
  },
};
</script>

<template>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.2.0/remixicon.min.css"
  />
  <div class="left-section">
    <div class="logo">
      <a href="/index.html">
        <img src="/images/logo.png" />
      </a>
      <a href="/index.html" class="logoshmogo"
        >Sound<span class="soup">Soup</span>
      </a>
    </div>

    <div class="sidebar">
      <div class="item">
        <a href="/index.html">
          <i class="ri-home-4-line"></i>
          <h3>Главная</h3>
        </a>
      </div>
      <div class="item">
        <i class="ri-user-6-line"></i>
        <h3>Мой Профиль</h3>
      </div>

      <div class="item">
        <a href="/album.html">
          <i class="ri-movie-2-line"></i>
          <h3>Популярное</h3>
        </a>
      </div>
      <div class="item">
        <i class="ri-film-line"></i>
        <h3>Плейлист</h3>
      </div>

      <div class="item">
        <i class="ri-heart-3-line"></i>
        <h3>Любимое</h3>
      </div>
      <div class="item">
        <i class="ri-settings-3-line"></i>
        <h3>Настройки</h3>
      </div>
    </div>

    <div class="sign-out">
      <i class="ri-logout-box-r-line"></i>
      <h3>Выйти</h3>
    </div>
  </div>

  <div class="header">
    <div class="search">
      <button><i class="ri-search-2-line"></i></button>
      <input class="music-search" type="text" placeholder="Поиск музыки..." />
    </div>

    <button class="custom-btn btn-5">
      <span><a href="loginreg.html">Войти</a></span>
    </button>
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Vazirmatn:wght@100..900&display=swap");

* {
  margin: 0;
  padding: 0;
  outline: none;
  box-sizing: border-box;
  font-family: "Vazirmatn", sans-serif;
}

.item a:visited {
  text-decoration: none;
  color: inherit;
}

img {
  object-fit: cover;
}

body {
  height: 100vh;
  background-color: rgb(0, 0, 0);
}

.left-section {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 20px 0;
  position: fixed;
  top: 0;
  transition: all 0.3s ease;
  padding-left: 10px;
}

.sidebar .item a {
  display: flex;
  align-items: center;
  text-decoration: none;
  gap: 10px;
  color: inherit; /* Inherit color from parent */
  width: 100%; /* Ensure the link takes the full width */
}

.left-section .logo {
  display: flex;
  align-items: center;
  gap: 8px;
}

.left-section .logo img {
  width: 38px;
  height: 38px;
}

.left-section .logo a {
  display: flex;
  align-items: center;
  font-weight: 800;
  font-size: 22px;
  color: #ffffff;
}

.soup {
  color: rgb(255, 138, 0);
}

.left-section .sidebar {
  display: flex;
  flex-direction: column;
  gap: 16px;
  line-height: 1;
}

.left-section .sidebar,
.sign-out h3 {
  font-family: "Montserrat", sans-serif;
  font-weight: 500;
  color: #e8f3f8;
}

.ri-home-4-line,
.ri-user-6-line,
.ri-movie-2-line,
.ri-film-line,
.ri-heart-3-line,
.ri-settings-3-line,
.ri-logout-box-r-line,
ri-heart-add-2-line {
  color: #fff;
}

.left-section .sidebar .item,
.left-section .sign-out {
  display: flex;
  align-items: center;
  gap: 10px;
  cursor: pointer;
}

.left-section .sidebar .item i,
.left-section .sign-out i {
  font-size: 20px;
  padding: 10px;
  border-radius: 12px;
  transition: all 0.4s ease;
}

.left-section .sidebar .item h3,
.left-section .sign-out h3 {
  font-weight: 500;
  font-size: 14px;
  transition: all 0.4s ease;
}

.left-section .sidebar .item.active i {
  background: #fed24d;
}

.left-section .sidebar .item.active h3 {
  color: rgb(255, 138, 0);
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 16px;
  border-right: 2px solid #e5e5e5;
}

.header .search {
  width: 60%;
  display: flex;
  align-items: center;
  background: rgba(236, 154, 3, 0.787);
  border-radius: 12px;
  margin-left: 16%;
}

.music-search {
  font-weight: 300;
  font-size: 15px;
  color: rgb(0, 0, 0);
}

::placeholder {
  color: #000;
}

.header .search input {
  border: none;
  padding: 10px 0;
  width: 100%;
  background: transparent;
}

.header .search button {
  border: none;
  padding: 10px;
  background: none;
  font-size: 18px;
  cursor: pointer;
}

.header > i {
  background: #15333a;
  color: #fff;
  padding: 8px;
  border-radius: 50%;
  cursor: pointer;
}

.ri-heart-add-2-line {
  color: white;
}

.ri-eye-line {
  color: white !important;
}

.custom-btn {
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: "Montserrat", sans-serif;
  font-weight: 500;
  font-size: 16px;
  letter-spacing: 0.2px;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow:
    inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1),
    4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
  margin-left: 12%;
}
.btn-5 {
  width: 90px;
  height: 38px;
  line-height: 42px;
  padding: 0;
  border: none;
  background: rgb(255, 135, 7);
  background: linear-gradient(
    0deg,
    rgb(255, 140, 9) 0%,
    rgb(255, 166, 13) 100%
  );
}
.btn-5:hover {
  color: #e6e6e6;
  background: rgba(255, 203, 31, 0.853);
  box-shadow: none;
}
.btn-5:before,
.btn-5:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: #ffffff;
  box-shadow:
    -1px -1px 5px 0px #fff,
    7px 7px 20px 0px #0003,
    4px 4px 5px 0px #0002;
  transition: 400ms ease all;
}
.btn-5:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.btn-5:hover:before,
.btn-5:hover:after {
  width: 100%;
  transition: 800ms ease all;
}

@media screen and (max-width: 1200px) {
  .left-section .logo .logoshmogo,
  .left-section .sidebar .item h3,
  .left-section .sign-out h3 {
    display: none;
  }
  .left-section .logo {
    justify-content: center;
    margin-bottom: 14px;
  }
}

@media screen and (max-width: 992px) {
  body {
    flex-direction: column;
  }

  .left-section {
    padding-top: 70px;
  }
}

@media screen and (max-width: 768px) {
  .logo a {
    display: none;
  }
}
</style>
