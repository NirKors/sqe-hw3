/* @Provengo summon selenium */

// Admin logs in using the admin credentials.
defineEvent(SeleniumSession, "adminLogin", function(session, e){
  session.writeText("//*[@id=\"email\"]", e.username)
  session.writeText("//*[@id=\"passwd\"]",e.password)
  session.click("//*[@id=\"submit_login\"]")

})

// Admin selects `Catalog` -> `Products` on the page's side menu.
defineEvent(SeleniumSession, "adminEntersProductsPage", function(session){
  session.click("//*[@id=\"subtab-AdminCatalog\"]")
  session.click("//*[@id=\"subtab-AdminProducts\"]")
})

// Admin selects dropdown menu for the first item, clicks delete within the dropdown, and confirms deletion in the popup message.
defineEvent(SeleniumSession, "adminSelectsAndDeletesNewestProduct", function(session){
  session.click("//*[@id=\"product_catalog_list\"]/div/table/tbody/tr[1]/td[11]/div/div/button")// Selects dropdown
  session.click("//*[@id=\"product_catalog_list\"]/div/table/tbody/tr[1]/td[11]/div/div/div/a[3]") // Selects delete within dropdown
  session.click("'//*[@id=\"catalog_deletion_modal\"]/div/div/div[3]/button[2]'") // Confirms deletion of product in popup
})

// User enters the newest (first) product by clicking its name.
defineEvent(SeleniumSession, "userEntersNewestProduct", function(session){
  session.click("//*[@id=\"content\"]/section[3]/div/div[1]")
})

// User presses the first share button (FaceBook).
defineEvent(SeleniumSession, "userSharesNewestProduct", function(session){
  session.click("//*[@id=\"add-to-cart-or-refresh\"]/div[3]/div/ul/li[1]/a")
})


