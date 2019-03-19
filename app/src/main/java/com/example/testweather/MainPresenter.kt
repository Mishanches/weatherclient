package com.example.testweather

import com.example.testweather.api.ServiceGenerator
import com.example.testweather.interfaces.IMainActivity
import com.example.testweather.interfaces.IMainPresenter
import com.example.testweather.model.WeatherDate
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val serviceGenerator: ServiceGenerator): IMainPresenter {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private var view: IMainActivity? = null
    private var code = CityCode.MOSCOW.code.toString()

    override fun loadWeather(codeCity: String) {

        disposables.add(
            serviceGenerator.serverApi
                .getWeather(codeCity)
                .subscribeOn(Schedulers.io())
                .flatMapPublisher { Flowable.fromIterable(it.weatherDates) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onWeatherLoaded, this::onWeatherError)
        )

         code = codeCity

    }

    override fun getCodeCity(): String {
        return code
    }

    override fun setUpView(view: IMainActivity?) {
        this.view = view
    }

    override fun setTitleForCity(codeCity: Int){

        val nameCity: Int

        when(codeCity) {

            CityCode.MOSCOW.code -> nameCity = R.string.drawer_title_moscow

            CityCode.SAINT_PETERSBURG.code -> nameCity = R.string.drawer_title_saint_petersburg

            CityCode.KAZAN.code -> nameCity = R.string.drawer_title_kazan

            CityCode.IRKUTSK.code -> nameCity = R.string.drawer_title_irkutsk

            CityCode.KRASNOYARSK.code -> nameCity = R.string.drawer_title_krasnoyarsk

            CityCode.NOVOSIBIRSK.code -> nameCity = R.string.drawer_title_novosibirsk

            CityCode.PERM.code -> nameCity = R.string.drawer_title_perm

            CityCode.PSKOV.code -> nameCity = R.string.drawer_title_pskov

            CityCode.SAMARA.code -> nameCity = R.string.drawer_title_samara

            else -> nameCity = R.string.app_name
        }

        view?.upDateActionBar(nameCity)

    }

    override fun onDestroy() {
        disposables.dispose()
    }

    private fun onWeatherLoaded(weatherDate: List<WeatherDate>){
        view?.showWeathers(weatherDate)
    }

    private fun onWeatherError(throwable: Throwable) {
        view?.showError(throwable.message)
    }

}