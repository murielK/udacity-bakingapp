package hr.murielkamgang.mf.data.source.receipe;

import hr.murielkamgang.mf.data.source.base.BaseKVH;
import io.reactivex.Observable;

public interface RecipeDataSourceExAsObservable {

    Observable<Boolean> toggleFavoriteAsObservable(BaseKVH baseKVH);
}
