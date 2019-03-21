package com.example.testweather

import com.example.testweather.api.ServiceGenerator
import com.example.testweather.interfaces.IMainActivity
import com.example.testweather.interfaces.IMainPresenter
import com.example.testweather.model.WeatherDate
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val serviceGenerator: ServiceGenerator) : IMainPresenter {

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

    override fun setUpView(view: IMainActivity?) {
        this.view = view
    }

    override fun getCodeCity(): String {
        return code
    }

    override fun onNavigationItem(item: Int) {

        var nameCity = R.string.drawer_title_moscow

        when (item) {

            R.id.moscow -> {
                loadWeather(CityCode.MOSCOW.code.toString())
                nameCity = R.string.drawer_title_moscow
            }

            R.id.saint_petersburg -> {
                loadWeather(CityCode.SAINT_PETERSBURG.code.toString())
                nameCity = R.string.drawer_title_saint_petersburg
            }

            R.id.kazan -> {
                loadWeather(CityCode.KAZAN.code.toString())
                nameCity = R.string.drawer_title_kazan
            }

            R.id.irkutsk -> {
                loadWeather(CityCode.IRKUTSK.code.toString())
                nameCity = R.string.drawer_title_irkutsk
            }

            R.id.krasnoyarsk -> {
                loadWeather(CityCode.KRASNOYARSK.code.toString())
                nameCity = R.string.drawer_title_krasnoyarsk
            }

            R.id.novosibirsk -> {
                loadWeather(CityCode.NOVOSIBIRSK.code.toString())
                nameCity = R.string.drawer_title_novosibirsk
            }

            R.id.perm -> {
                loadWeather(CityCode.PERM.code.toString())
                nameCity = R.string.drawer_title_perm
            }

            R.id.pskov -> {
                loadWeather(CityCode.PSKOV.code.toString())
                nameCity = R.string.drawer_title_pskov
            }

            R.id.samara -> {
                loadWeather(CityCode.SAMARA.code.toString())
                nameCity = R.string.drawer_title_samara
            }

        }

        view?.upDateActionBar(nameCity)

    }

    override fun onDestroy() {
        disposables.dispose()
        view = null
    }

    private fun onWeatherLoaded(weatherDate: List<WeatherDate>) {
        view?.showWeathers(weatherDate)
    }

    private fun onWeatherError(throwable: Throwable) {
        view?.showError(throwable.message)
    }

}