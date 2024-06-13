const allVideos = document.querySelectorAll("video");
const sidebar = document.querySelector(".left-section");
const sidebarItems = document.querySelectorAll(".sidebar .item");
const catItems = document.querySelectorAll(".categories a");

// играть или остановить видео на отвод мышки
allVideos.forEach((video) => {
  video.addEventListener("mouseover", () => {
    video.play();
  });
  video.addEventListener("mouseleave", () => {
    video.pause();
  });
});

// выделение элемента по нажатию
sidebarItems.forEach((sideItem) => {
  sideItem.addEventListener("click", () => {
    sidebarItems.forEach((item) => {
      item.classList.remove("active");
    });
    sideItem.classList.add("active");
  });
});

// для маленьких девайсов
window.addEventListener("scroll", () => {
  if (window.innerWidth <= 992) {
    if (window.pageYOffset > 20) {
      sidebar.style.paddingTop = "20px";
    } else {
      sidebar.style.paddingTop = "70px";
    }
  }
});

// выделение нужной категории при нужной странице
document.addEventListener("DOMContentLoaded", () => {
  const currentPath = window.location.pathname;
  console.log("Current Path:", currentPath); // отображение страницы текущей

  sidebarItems.forEach((item) => {
    item.classList.remove("active");
  });

  if (currentPath.includes("/album.html")) {
    const popularTab = document.querySelector(".sidebar .item:nth-child(3)");
    console.log("Popular Tab:", popularTab);
    if (popularTab) {
      popularTab.classList.add("active");
      console.log("Popular Tab Activated");
    }
  } else if (currentPath.includes("index.html") || currentPath === "/") {
    const homeTab = document.querySelector(".sidebar .item:nth-child(1)");
    console.log("Home Tab:", homeTab);
    if (homeTab) {
      homeTab.classList.add("active");
      console.log("Home Tab Activated");
    }
  }
});
