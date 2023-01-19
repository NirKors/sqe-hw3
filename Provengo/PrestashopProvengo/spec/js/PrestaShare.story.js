/* @provengo summon selenium */

// Admin deletes the latest product in his shop.
story('Admin deletes the latest product added', function(){
  with (new SeleniumSession().start('http://localhost:8080/adminQA/')){
    adminLogin({username : 'danik107@gmail.com', password : 'IHaveNoEnergy'})
    adminEntersProductsPage();
    adminSelectsAndDeletesNewestProduct();
  }
})

// User enters the newest item in the page shop and presses share via Facebook.
story('User enters the newest item page and shares it on Facebook', function(){
  with (new SeleniumSession().start('http://localhost:8080/')){
    userEntersNewestProduct()
    userSharesNewestProduct()
  }
})
