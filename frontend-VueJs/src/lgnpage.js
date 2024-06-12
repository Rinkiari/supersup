/* eslint-disable no-unused-vars */
const container = document.querySelector('.container')
const signupButton = document.querySelector('.signup-section header')
const loginButton = document.querySelector('.login-section header')

loginButton.addEventListener('click', () => {
  container.classList.add('active')
})

signupButton.addEventListener('click', () => {
  container.classList.remove('active')
})

function goBack() {
  if (document.referrer) {
    window.history.back()
  } else {
    window.location.href = 'index.html' // фоллбек URL
  }
}
