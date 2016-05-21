# Booky
A platform to buy and sell books

<h3>Features that the app is supposed to have :</h3>
<ul>
<li>Search a book, find the nearest sellers selling it</li>
<li>Search a seller, view what he/she is selling and contact him/her</li>
<li>Find the nearest shops which sell books, also suggest any new shop(Like google maps)</li>
</ul>

<h3>TODO:</h3>
<ul>
<li><strike>Login page</strike></li>
<li>Seller profile form</li>
<li>Seller Page: A page to keep track of the different books he/she is selling</li>
<li>Seller Form: Details of a book</li>
<li>Buyer main Page : A page with Nav Bar to choose from maps 1, 2 and 3</li>
<li>Buyer Page 1, a ListView with all the available book names</li>
<li>Buyer map 1 : Display Sellers of a <strong>chosen Book</strong></li>
<li>Buyer map 2 : Display <strong>all sellers</strong></li>
<li>Buyer map 3 : Display all the nearby <strong>shops</strong></li>
<li>Seller details : Displaying the details of the seller once chosen from map 2</li>
<li>Book details : Displaying the details of the book once chosen from map 1, containing seller details too</li>
<li>Adding markers to suggest new shops in map 3</li>
</ul>

<h3>TODO Server:</h3>
<ul>
<li>Octa login</li>
<li>Seller table</li>
<li>Books table</li>
</ul>

<h4>Seller table:</h4>
<table style="width:100%">
  <tr>
    <th>Name</th>
    <th>Phone</th>
    <th>EmailID</th>
    <th>Rating</th>
    <th>Latitude</th>
    <th>Longitude</th>
    <th>Picture</th>
  </tr>
</table>

<h4>Books table:</h4>
<table style="width:100%">
  <tr>
    <th>Name</th>
    <th>SellerEmailId</th>
    <th>Condition</th>
    <th>Author</th>
    <th>Edition</th>
    <th>Price</th>
    <th>Picture</th>
  </tr>
</table>

<h3>Build Instructions :</h3>
<ul>
<li>Fork this repo</li>
<li>Clone the forked repo and save it in your PC</li>
<li>Make changes</li>
<li><code>git add . </code>,<code> git commit -m "Message", git push origin master</code></li>
<li>If any changes made to the main repo, then merge changes in your fork,and then,
<code>git stash, git pull origin master, git stash apply</code></li>
<li>Once changes are made, send a Pull Request, I'll see the changes, and merge it. </li>
</ul>

<h3>NOTE:</h3>
<p>Upgrade your Android Studio such that it has Gradle v2.10 and SDK for API 23. Or once cloned ,while building, just do whatever
Studio asks you to.</p>
