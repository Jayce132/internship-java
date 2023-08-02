const page = location.pathname.split('/').at(-1);

let profileLink = document.querySelector('#profile');
let orderLink = document.querySelector('#order');

if (page === 'edit-profile') {
    profileLink.classList.add('active');
    profileLink.classList.remove('link-dark');
} else if (page === 'order') {
    orderLink.classList.add('active');
    orderLink.classList.remove('link-dark');
}