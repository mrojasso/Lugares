package com.dispositivosmoviles.utilidades
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.dispositivosmoviles.R
import com.lugares.utiles.OtrosUtiles
import java.io.File
import java.io.IOException

class AudioUtilities (


    private val actividad: Activity,
    private val contexto: Context,
    private val btAccion: ImageButton,
    private val btPlay: ImageButton,
    private val btDelete: ImageButton,
    private val msgIniciolNotaudio:String,
    private val msgDetieneNotaAudio:String
    ){
    init {
        btAccion.setOnClickListener { grabaDetiene() }
        btPlay.setOnClickListener { reproducirliota() }
        btDelete.setOnClickListener { borranNota() }

        btPlay.isEnabled= false
        btDelete.isEnabled = false
    }
    private var mediaRecorder: MediaRecorder? = null
    private var grabando = false
    var audioFile: File = File.createTempFile("audio","mp3")

    private fun RecorderInit(){
        if(audioFile.exists() && audioFile.isFile){
            audioFile.delete()
        }
        val archivo = OtrosUtiles.getTempFile("audio")
        audioFile = File.createTempFile(archivo,"mp3")
        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioChannels(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder!!.setOutputFile(audioFile)


    }

    private fun IniciaGrabacion(){
        try{
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            Toast.makeText(contexto,msgIniciolNotaudio,Toast.LENGTH_LONG).show()
            btAccion.setImageResource(R.drawable.ic_stop)
            btPlay.isEnabled= false
            btDelete.isEnabled= false
        }
        catch (e:java.lang.IllegalStateException){
            e.printStackTrace()
        }
        catch (e:IOException){
            e.printStackTrace()
        }

    }

    private fun detenerNota(){
        btPlay.isEnabled= true
        btDelete.isEnabled= true
        mediaRecorder?.stop()
        mediaRecorder?.release()
        Toast.makeText(contexto,msgDetieneNotaAudio,Toast.LENGTH_LONG).show()
        btAccion.setImageResource(R.drawable.ic_mic)

    }



        private fun grabaDetiene(){
            if(ContextCompat.checkSelfPermission(contexto,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
                ActivityCompat.requestPermissions(actividad,permissions,0)
            }else{
                grabando= if(!grabando){
                    RecorderInit()
                    IniciaGrabacion()
                    true
                }else{
                    detenerNota()
                    false
                }
            }

        }

    private fun reproducirliota(){
        try {
            if(audioFile.exists() && audioFile.canRead()){
                val mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(audioFile.path)
                mediaPlayer.prepare()
                mediaPlayer.start()

            }
        }catch (e: IOException){
            e.printStackTrace()
        }

    }

    private fun borranNota(){

        try {
            if(audioFile.exists()){
                audioFile.delete()
                btPlay.isEnabled= false
                btDelete.isEnabled= false


            }
        }catch (e: IOException){
            e.printStackTrace()
        }

    }
}