import { useState, useEffect } from 'react';
import {useParams} from 'react-router-dom';

function DetailMovie() {
    const {movieId} = useParams();
    const [movie, setMovie] = useState();

    useEffect(() => {
        async function fetchMovie() {
            const response = await fetch(`http://localhost:8080/movies/${movieId}`);
            const data = await response.json();
            setMovie(data);
            console.log(data);
        }
        fetchMovie();
    }, []);

    return (
        <main className="w-4/5 m-auto p-5">
            {movie && ( 
            <>
                <h1 className="font-bold text-xl">{movie.title}</h1>
                <p>{movie.description}</p>
                <p>Nota média: {movie.averageScore.toFixed(1)}/5</p>

                <div className="bg-gray-100 p-3 rounded w-1/2">
                    <h2 className="font-bold text-lg">Reviews</h2>
                    {movie.reviews.map(review => (
                        <div className="bg-gray-50 p-2 m-2 rounded shadow">
                            <p className="float-right">{review.score}/5</p>
                            <p>{review.text}</p>
                            <span className="text-gray-500 text-sm">{review.author}</span>
                        </div>
                    ))}
                </div>
            </>)}
        </main>
    );
}

export default DetailMovie;