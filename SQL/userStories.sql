### User related Query 
# get password 
select U.pwd from Users U where U.email = {email};
# add Users
insert into Users(email, name, pwd) Values ({email}, {name}, {pwd});
# remove Users by email;
delete from Users where email = {email}; 
# update Users email, name or pwd.
update Users set name = {name} where email = {email};
update Users set pwd = {pwd} where email = {email};

# return songname by songID;
select S.songName from Songs S where S.songID in {songID};
#For example return all the favorite songName
select S.songName from Songs S where S.songID in (select F.songID from Favorite F where F.userEmail = ?);

# do we need to return songID to user?
# As user, I want to be able to search music by name, artist, and/or genre.
-- #search by song name
select S.songID from Songs S where S.songName = {name};
-- #search by artist name
select S.songID from Songs S, Artists A where S.artistID = A.artistID and A.artistName = {artistName};
-- #search by genre name;
select S.songID from Songs S, Genres G where S.genreID = G.genreID and G.name = {genreID};

-- this one is duplicated with the one above
#### As user, I want a favorite list so I can keep track of musics I like
select F.songID from Favorite F where F.userEmail = {email};

#### As user, I want to create custom groups for my musics
insert into PlaylistOwnership(playlistName, userEmail) values({playlistName}, {userEmail});
# add songs into Playlist.
set @pID = (select P.playlistID from PlaylistOwnership P where P.playlistName = {playlistName});
insert into PlaylistSongs(playlistID, songID) values (pID, {songID});

#### As user, I want to be able to search an album, and get the lists of songs in the that specific album (along with its singer name)
# get the lists of songs in the that specific album
select S.songName from Songs S, Albums A where S.albumID = A.albumID and A.albumName = {albumName};
# get singer name by songName;
select A.artistName from Artists A, Songs S where A.artistID = S.artistID and S.songName = {songName};
# get all the album names of a singer
select distinct S.albumName from Songs S, Artists A where S.artistID = A.artistID and A.artistName = {Name};

#### As user, I want to see the information about a song(author, year, which album it belongs to, genre, duration) 
# get all wanted information by searching the song name
SELECT A.artistName, S.publishDate, B.albumName, G.name, S.duration 
FROM Songs S, Artists A, Albums B, Genres G
WHERE(S.songName = {name} AND S.albumID = B.albumID AND
	S.genreID = G.genreId AND S.artistID = A.artistID);

#### As user, I want to see the newly released songs within a week
SELECT * FROM Songs WHERE publishDate BETWEEN
{7days before} AND {today’s date};

#### As user, I want to see the most popular songs within a week (can do the most popular song among all songs) get the top 20
SELECT * FROM Songs AS S WHERE publishDate BETWEEN
{7days before} AND {today’s date}
ORDER BY S.popularity DESC
LIMIT 20;
