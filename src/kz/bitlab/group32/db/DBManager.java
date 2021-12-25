package kz.bitlab.group32.db;

import java.sql.*;
import java.util.ArrayList;

public final class DBManager {
    private static ArrayList<Films> films;
    private static ArrayList<Country> countries;
    private static ArrayList<Genre> genres = new ArrayList<>();
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/group_32_db","serik","Drexler22");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        countries=getCountries();
        genres = getGenres();
        films = getFilms();

    }
    public static ArrayList<Country> getCountries(){
        ArrayList<Country> countries_local = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from s_country");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                countries_local.add(
                    new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("name_rus")
                    )
                );
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        countries=countries_local;
        return countries_local;
    }
    public static ArrayList<Genre> getGenres(){
        ArrayList<Genre> genres_local = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from s_genre");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                genres_local.add(
                    new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("name_rus")
                    )
                );
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        genres=genres_local;
        return genres_local;
    }
    public static ArrayList<Films> getFilms(){
        ArrayList<Films> films_local = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select f.*, u.full_name from films f " +
                "inner join users u on f.user_id=u.id " +
                "order by name asc");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Integer country_id= resultSet.getInt("country");
                Integer genre_id= resultSet.getInt("genre");
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer duration = resultSet.getInt("duration");
                Long user_id = resultSet.getLong("user_id");
                String full_name = resultSet.getString("full_name");
                Country country = countries.stream().filter(x-> x.getId()==country_id).findFirst().orElse(null);
                String  desc = resultSet.getString("description");
                Genre genre = genres.stream().filter(x->x.getId()==genre_id).findFirst().orElse(null);
                Integer like_amount = resultSet.getInt("like_amount");
                films_local.add(
                    new Films(
                        id,
                        name,
                        duration,
                        country,
                        desc,
                        genre,
                        new User(user_id, null, null, full_name),
                        like_amount
                    )
                );
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        films=films_local;
        return films_local;
    }
    public static ArrayList<Films> searchFilms(String search){
        var ret = new ArrayList<Films>(films.stream().filter((a)->a.getName().toLowerCase().contains(search)).toList());
        return ret;
    }
    public static boolean saveFilm(Films film)
    {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "update films set " +
                    "name = ?, duration = ?, description = ?, " +
                    "country = ?, genre = ? "+
                "where id = ?"
            );
            statement.setString(1,film.getName());
            statement.setInt(2,film.getDuration());
            statement.setString(3,film.getDescription());
            statement.setInt(4,film.getCountry().getId());
            statement.setInt(5,film.getGenre().getId());
            statement.setLong(6,film.getId());
            rows = statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows>0;
    }
    public static boolean deleteFilm(Long id)
    {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "delete from films where id = ?"
            );

            statement.setLong(1,id);
            rows = statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows>0;
    }
    public static boolean addFilm(Films film){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "insert into films( name, duration, country, description, genre, user_id) " +
                "values(?,?,?,?,?,?)");
            statement.setString(1, film.getName());
            statement.setInt(2,film.getDuration());
            statement.setInt(3, film.getCountry().getId());
            statement.setString(4, film.getDescription());
            statement.setInt(5, film.getGenre().getId());
            statement.setLong(6, film.getUser().getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rows>0;
    }
    public static Films getFilm(Long id){
        Films film = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select f.*, u.full_name from films f " +
                "inner join users u on f.user_id=u.id " +
                "where f.id=? ");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Integer country_id= resultSet.getInt("country");
                Integer genre_id= resultSet.getInt("genre");
                film =  new Films(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("duration"),
                    countries.stream().filter(x-> x.getId()==country_id).findFirst().orElse(null),
                    resultSet.getString("description"),
                    genres.stream().filter(x->x.getId()==genre_id).findFirst().orElse(null),
                    new User(resultSet.getLong("user_id"), null, null, resultSet.getString("full_name")),
                    resultSet.getInt("like_amount")
                );
            }
            statement.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return film;
    }
    public static User getUser(String email){
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "select * from users where email=?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("full_name")
                );
            }
            statement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
    public static boolean addUser(User user){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO USERS(email,password, full_name)" +
                "values(?,?,?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rows>0;
    }
    public static boolean updateUser(User user){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "UPDATE USERS SET full_name = ? WHERE id = ? ");
            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rows>0;
    }
    public static boolean updatePassword(User user){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "UPDATE USERS u SET u.password = ? WHERE u.id = ? ");
            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rows>0;
    }
    public static boolean addComment(Comments comment){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO comments(film_id, user_id, comment, post_date)" +
                "values(?,?,?,NOW())");
            statement.setLong(1, comment.getFilm().getId());
            statement.setLong(2, comment.getUser().getId());
            statement.setString(3, comment.getComment());
            ;
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rows>0;
    }
    public static ArrayList<Comments> getComments(Long filmId){
        ArrayList<Comments> comments  = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select c.*, u.full_name from comments c " +
                "inner join users u on c.user_id=u.id " +
                "where c.film_id= ? "+
                "order by c.post_date desc");
            statement.setLong(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comments(
                    resultSet.getLong("id"),
                    new Films(filmId),
                    new User(
                        resultSet.getLong("user_id"),
                        null, null,
                        resultSet.getString("full_name")
                    ),
                    resultSet.getString("comment"),
                    resultSet.getTimestamp("post_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }
    public static int toLikeFilm(Films film, User user){
        int likes = 0;
        boolean isLiked = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from likes where film_id = ? and user_id = ?");
            statement.setLong(1,film.getId());
            statement.setLong(2, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                isLiked = true;
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(isLiked) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "delete from likes where film_id = ? and user_id = ?");
                statement.setLong(1, film.getId());
                statement.setLong(2, user.getId());
                statement.executeUpdate();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "insert into likes (film_id, user_id) values(?, ?) ");
                statement.setLong(1, film.getId());
                statement.setLong(2, user.getId());
                statement.executeUpdate();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "select count(*) like_count from likes where film_id=?");
            statement.setLong(1,film.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                likes = resultSet.getInt("like_count");
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "update films set like_amount=? where id=?");
            statement.setInt(1, likes);
            statement.setLong(2, film.getId());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return likes;
    }
}
