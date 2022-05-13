### User related Query 
# get password 
select U.pwd from Users U where U.email = {email};
# add Users
insert into Users(email, name, pwd) Values ({email}, {name}, {pwd});
# remove Users by email;
delete from Users where email = {email}; 
# remove Users by ID;
delete from Users where id = {id}; 
# update Users email, name or pwd.
update Users set email = {email} where id = {id};
update Users set name = {name} where id = {id};
update Users set pwd = {pwd} where id = {id};

### return songname by songID;
select S.songName from Songs S where S.songID in {songID};
#For example return all the favorite songName
select S.songName from Songs S where S.songID in (select F.songID from Favorite F where F.userEmail = "12341@gmail.com");

### As user, I want to be able to search music by name, artist, and/or genre.
-- #search by song name
select S.songID from Songs S where S.songName = {name};
-- #search by artist name
select S.songID from Songs S, Artists A where S.artistID = A.artistID and A.artistName = {artistName};
-- #search by genre name;
select S.songID from Songs S, Genres G where S.genreID = G.genreID and G.name = {genreID};

#### As user, I want a favorite list so I can keep track of musics I like
select F.songID from Favorite F where F.userEmail = {email};

#### As user, I want to create custom groups for my musics
insert into PlaylistOwnership(playlistName, userEmail) values({playlistName}, {userEmail});
# add songs into Playlist.
set @pID = (select P.playlistID from PlaylistOwnership P where P.playlistName = {playlistName);
insert into PlaylistSongs(playlistID, songID) values (pID, {songID});

#### As user, I want to be able to search an album, and get the lists of songs in the that specific album (along with its singer name)
# get the lists of songs in the that specific album
select S.songName from Songs S, Albums A where S.albumID = A.albumID and A.albumName = {albumName};
# get singer name by songName;
select A.artistName from Artists A, Songs S where A.artistID = S.artistID and S.songName = {songName};
# get all the album names of a singer
select distinct S.albumName from Songs S, Artists A where S.artistID = A.artistID and A.artistName = {Name};

-----------------------Test Needed------------------------


#### As user, I want to see the information about a song(author, year, which album it belongs to, genre, duration)
SELECT A.artistName, S.publishxDate, B.albumName, G.name, S.duration 
FROM Songs S, Artists A, Albums B, Genres G
WHERE(S.songName = "no2", S.albumID = B.albumID, 
	S.genreID = G.genreId, S.artistID = A.artistID);

### As user, I want to see the newly released songs within a week
# Not working
# Todo
SELECT DATEDIFF(day, ‘2021/3/10’, ‘2021/3/20’) AS newly_Released_Songs FROM Songs;

### As user, I want to see the most popular songs within a week（can do the most popular song among all songs)
# Not working
# Todo
	SELECT * FROM Songs S
	DATEDIFF(day, ‘2022-5-26’, ‘2022-6-2’)
	ORDER BY S.popularity DESC
	LIMIT 10
