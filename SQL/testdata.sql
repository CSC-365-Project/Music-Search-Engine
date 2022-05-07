insert into Users(email, name, pwd) values ('12341@gmail.com', 'chen', '123asdf');
insert into Users(email, name, pwd) values ('1231asdf@gmail.com', 'david', '11saxzv');
insert into Users(email, name, pwd) values ('xcvna@gmail.com', 'john', '87szxcv');
insert into Users(email, name, pwd) values ('xzcv1312@hotmail.com', 'cindy', 's14cxvs');

insert into Genres(name, description) values ('rock', 'very cool'); #1
insert into Genres(name, description) values ('classic', 'boring'); #2
insert into Genres(name, description) values ('pop', 'hey yo'); #3
insert into Genres(name, description) values ('soul', 'chill'); #4
insert into Genres(name, description) values ('blues', 'where are you'); #5

#Artists(artistID, artistName, follower, description)
insert into Artists(artistName, follower, description) values ('jackson', 124123 ,'very cool'); #1
insert into Artists(artistName, follower, description) values ('stephen', 1 ,'very cool'); #2
insert into Artists(artistName, follower, description) values ('johnny', 0 ,'very cool'); #3
insert into Artists(artistName, follower, description) values ('tom', 123124 ,'very cool'); #4
insert into Artists(artistName, follower, description) values ('keithy', 2553 ,'very cool'); #5
insert into Artists(artistName, follower, description) values ('yao', 645 ,'very cool'); #6

#Albums(albumID, albumName, artistID, publishDate)
insert into Albums(albumName, artistID, publishDate) values ('badass', 1, '2020-01-12'); #1
insert into Albums(albumName, artistID, publishDate) values ('heaven', 1, '2020-01-12'); #2
insert into Albums(albumName, artistID, publishDate) values ('hey yo whats up', 1, '2022-01-12'); #3
insert into Albums(albumName, artistID, publishDate) values ('bad guy', 2, '2000-01-12'); #4
insert into Albums(albumName, artistID, publishDate) values ('my dream girl', 3, '2016-03-12'); #5
insert into Albums(albumName, artistID, publishDate) values ('fuck you', 4, '2014-01-12'); #6

#Songs(songID, songName, url, popularity, duration, publishDate, albumID, artistID, genreID)
insert into Songs(songName, url, popularity, duration, publishDate, albumID, artistID, genreID) values 
('no1', 'sadfljwe.com', 0, 180, '2021-3-19', 1, 1, 1); #1
insert into Songs(songName, url, popularity, duration, publishDate, albumID, artistID, genreID) values 
('no2', 'sdfaxcv13.com', 0, 155, '2021-3-19', 1, 1, 1); #2
insert into Songs(songName, url, popularity, duration, publishDate, albumID, artistID, genreID) values 
('no3', 'sadfljw13e124.com', 0, 235, '2021-3-19', 1, 1, 2); #3
insert into Songs(songName, url, popularity, duration, publishDate, albumID, artistID, genreID) values 
('dream', 'sadfljwe123.com', 123, 180, '2021-3-19', 2, 1, 3); #4
insert into Songs(songName, url, popularity, duration, publishDate, albumID, artistID, genreID) values 
('i dont know', 'safd123.com', 0, 131, '2021-3-19', 3, 2, 1); #5
insert into Songs(songName, url, popularity, duration, publishDate, albumID, artistID, genreID) values 
('you guess what', 'safd1zxcv23.com', 0, 250, '2014-1-19', 4, 3, 4); #6

#Favorite(id, userEmail, songId)
insert into Favorite(userEmail, songID) values('12341@gmail.com', 1);
insert into Favorite(userEmail, songID) values('12341@gmail.com', 2);
insert into Favorite(userEmail, songID) values('12341@gmail.com', 3);
insert into Favorite(userEmail, songID) values('12341@gmail.com', 4);
insert into Favorite(userEmail, songID) values('xcvna@gmail.com', 5);
insert into Favorite(userEmail, songID) values('xcvna@gmail.com', 6);

#PlaylistOwnership(playlistName, userEmail)
insert into PlaylistOwnership(playlistName, userEmail) values('myfirstlist','12341@gmail.com');
insert into PlaylistOwnership(playlistName, userEmail) values('my2ndlist','12341@gmail.com');
insert into PlaylistOwnership(playlistName, userEmail) values('my3rdlist','12341@gmail.com');
insert into PlaylistOwnership(playlistName, userEmail) values('rock music','xcvna@gmail.com');
insert into PlaylistOwnership(playlistName, userEmail) values('sleeping','xcvna@gmail.com');

#PlaylistSongs(id, playlistID, songID)
insert into PlaylistSongs(playlistID, songID) values(1, 1);
insert into PlaylistSongs(playlistID, songID) values(1, 2);
insert into PlaylistSongs(playlistID, songID) values(1, 3);
insert into PlaylistSongs(playlistID, songID) values(1, 4);
insert into PlaylistSongs(playlistID, songID) values(1, 5);
insert into PlaylistSongs(playlistID, songID) values(2, 4);
insert into PlaylistSongs(playlistID, songID) values(2, 6);
insert into PlaylistSongs(playlistID, songID) values(3, 5);
insert into PlaylistSongs(playlistID, songID) values(4, 2);

