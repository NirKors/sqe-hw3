# Assignment 3: Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [PrestaShop](https://demo.prestashop.com/#/en/front).

This is an open-source, e-commerece web application which supports all online payment methods.

## Installation
We installed it using the official Prestashop Image on Docker hub.
We started by running Docker Hub Desktop and pulled the image using this command:
docker pull prestashop/prestashop

Afterwards, we created an SQL server also on Docker Desktop to be used as our database container.

We followed the [following tutorial](https://www.youtube.com/watch?v=J4lq2eW_npE&ab_channel=PatriTech) to create it. So
if you are also interested in making one, follow it's steps.

## What we tested
We tested the product module that tries sharing a product.

*User story:* A user tries to share a product.

*Preconditions:* The product exists in the store database and is shown in the online store.

*Expected outcome:* The user shares the item on social media.

*User story:* An admin deletes a product from the website.

*Preconditions:* There is a product in the website's database.

*Expected outcome:* The product is removed from the database and the store website.


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Detected Bugs
We detected the following bug:

1. Bug: 
   1. General description: Pressing the share button of a deleted product still tries to share it.
   2. Steps to reproduce: 
      1. With a user, enter a product page
      2. With an admin, enter the product catalog in the dashboard
      3. Delete the item
      4. With a user, try to share the item
   3. Expected result: An error message or pop up saying that the item was re,oved, or nothing at all.
   4. Actual result: A new tab is still being opened. On pinterest, it tries to share an empty picture.
   5. [Link to the bug report](https://www.prestashop.com/forums/topic/1072725-sharing-a-deleted-item/)
